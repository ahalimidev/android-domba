package com.izzancode.kompoengdomba.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.MainActivity
import com.izzancode.kompoengdomba.databinding.ActivityProfilBinding
import com.izzancode.kompoengdomba.utils.FileCompressor
import com.izzancode.kompoengdomba.utils.foto_profil
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class profil : AppCompatActivity() {
    private val binding by lazy { ActivityProfilBinding.inflate(layoutInflater) }
    lateinit var dialog : Dialog
    lateinit var vm: customerViewModel
    companion object {
        private const val REQUEST_TAKE_PHOTO = 1
        private const val REQUEST_OPEN_GALLERY = 2
        private const val PERMISSION_REQUEST_CODE = 100
    }
    private lateinit var fileCompressor: FileCompressor
    private lateinit var fileImage: File
    var fileImage_UPLOAD: File? = null
    private var selectedSelectImage: Int = 0
    private val listSelectImage = arrayOf("Take Photo", "Choose from Gallery")
    private var foto = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)
        dialog = progressDialog(this@profil)
        fileCompressor = FileCompressor(this)

        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.ivKeluar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Peringatan")
            builder.setMessage("Apakah kamu yakin keluar aplikasi?")
            builder.setPositiveButton("Ya") { dialog, which ->
                dialog.dismiss()
                logout()
            }
            builder.setNegativeButton("Tidak") { dialog, which ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

        binding.ivUbahPpassword.setOnClickListener {
            startActivity(Intent(this@profil,ganti_password::class.java).putExtra("foto",foto))
        }
        binding.ivUbahFoto.setOnClickListener { selectImage() }
        tampil()
    }
    private  fun tampil(){
        lifecycleScope.launch {
            dialog.show()
            try {
                val authResponse = vm.profil(this@profil)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result =authResponse.body()!!.data
                    foto = foto_profil+result.fv_image
                    Glide.with(this@profil)
                        .load(foto)
                        .into(binding.profileImage)
                    val tgl = result.fd_dateinput!!.split("-")
                    binding.tvNoReg.text = result.fv_codecus
                    binding.tvTglGabung.text = tgl[2]+'-'+tgl[1]+'-'+tgl[0]
                    binding.tvNoNip.text = result.fv_identitynum
                    binding.tvTelepon.text = result.fv_phone
                    binding.tvEmail.text = result.fv_email
                    binding.tvAlamat.text = result.fv_address+" "+result.fv_cityname+" "+result.fv_provincename+" "+result.fv_codecus+" "+result.fv_zipcode
                    binding.tvNama.text = result.fv_namecus

                } else {
                    dialog.dismiss()
                    Toast.makeText(
                        this@profil,
                        authResponse.body()!!.pesan,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR", throwable.toString())
            }
        }
    }
    private  fun logout (){
        lifecycleScope.launch {
            try {
                val x = vm.logout(this@profil)
                val sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(this@profil,login::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
                finish()
            } catch (throwable: Throwable) {
                Log.e("ERROR",throwable.toString())
            }
        }
    }
    private fun selectImage() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(listSelectImage) { _, item ->
            when {
                listSelectImage[item] == "Take Photo" -> {
                    selectedSelectImage = 0
                    if (checkPersmission()) {
                        takePhoto()
                    }
                    else {
                        requestPermission()
                    }
                }
                listSelectImage[item] == "Choose from Gallery" -> {
                    selectedSelectImage = 1
                    if (checkPersmission()) {
                        openGallery()
                    }
                    else {
                        requestPermission()
                    }
                }
            }
        }
        builder.show()
    }

    private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED  && ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ), PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                if (selectedSelectImage == 0) {
                    takePhoto()
                } else {
                    openGallery()
                }

            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        fileImage = createFile()
        val uri = if(Build.VERSION.SDK_INT >= 24){
            FileProvider.getUriForFile(this, "com.izzancode.kompoengdomba.fileprovider",
                fileImage)
        } else {
            Uri.fromFile(fileImage)
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQUEST_TAKE_PHOTO)
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_OPEN_GALLERY)
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("IMG_${timeStamp}", ".jpg", storageDir)
    }

    private fun bitmapToFile(bitmap: Bitmap): File {
        return try {
            fileImage = createFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapdata = bos.toByteArray()

            val fos = FileOutputStream(fileImage)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            fileImage
        } catch (e: Exception) {
            e.printStackTrace()
            fileImage
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                fileImage_UPLOAD = fileCompressor.compressToFile(fileImage)!!
                upload(fileImage_UPLOAD)


            }
        } else if (requestCode == REQUEST_OPEN_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, data!!.data!!))
                } else {
                    MediaStore.Images.Media.getBitmap(this.contentResolver, data!!.data!!)
                }
                fileImage_UPLOAD = fileCompressor.compressToFile(bitmapToFile(bitmap))!!
                //binding.tvNamaFoto.text = fileImage_UPLOAD!!.name
                upload(fileImage_UPLOAD)
            }
        }
    }
    fun upload (uploakk: File?){
        dialog.show()

        lifecycleScope.launch {
            try {
                val authResponse = vm.upload(
                    this@profil,
                    MultipartBody.Part.createFormData("fv_image", uploakk!!.name,
                        RequestBody.create("image/*".toMediaTypeOrNull(),uploakk!!))
                )
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val i = Intent(this@profil, profil::class.java)
                    finish()
                    overridePendingTransition(0, 0)
                    startActivity(i)
                    overridePendingTransition(0, 0)
                } else {
                    dialog.dismiss()
                    Toast.makeText(
                        this@profil,
                        authResponse.body()!!.pesan,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}