package com.izzancode.kompoengdomba.server

import com.izzancode.kompoengdomba.model.respon.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("customer/login.php")
    suspend fun login(
        @Field("fv_email") fv_email: String,
        @Field("fv_password") fv_password: String,
        @Field("fv_token") fv_token: String,
    ): Response<user>

    @FormUrlEncoded
    @POST("customer/email_verifikasi.php")
    suspend fun verfikaasi_email(
        @Field("fv_email") fv_email: String
    ): Response<user>

    @FormUrlEncoded
    @POST("customer/lupa_password.php")
    suspend fun lupa_password(
        @Field("fv_code") fv_code: String,
        @Field("fv_password") fv_password: String,
        @Field("fv_token") fv_token: String,
    ): Response<user>

    @FormUrlEncoded
    @POST("customer/ganti_paassword.php")
    suspend fun ganti_paassword(
        @Field("fn_cusid") fn_cusid: String,
        @Field("fv_password") fv_password: String,
    ): Response<user>

    @FormUrlEncoded
    @POST("customer/logout.php")
    suspend fun logout(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<user>

    @Multipart
    @POST("customer/upload.php")
    suspend fun upload(
        @Part("fn_cusid") fn_userid: RequestBody,
        @Part fv_image : MultipartBody.Part

    ): Response<user>

    @FormUrlEncoded
    @POST("customer/profil.php")
    suspend fun profil(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<user>

    @FormUrlEncoded
    @POST("saldo/total_saldo.php")
    suspend fun total_saldo(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<saldo>

    @FormUrlEncoded
    @POST("saldo/detail_saldo.php")
    suspend fun detail_saldo(
        @Field("fn_cusid") fn_cusid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
    ): Response<saldo>

    @FormUrlEncoded
    @POST("notif/detail_notif.php")
    suspend fun detail_notif(
        @Field("fn_cusid") fn_cusid: String,
        @Field("days") days: String,
    ): Response<notif>

    @FormUrlEncoded
    @POST("kandang/kandang.php")
    suspend fun kandang(
        @Field("fc_status") fc_status: String,
    ): Response<kandang>

    @FormUrlEncoded
    @POST("kandang/pencarian_kandang.php")
    suspend fun pencarian_kandang(
        @Field("fc_status") fc_status: String,
        @Field("fv_codekav") fv_codekav: String,
    ): Response<kandang>

    @FormUrlEncoded
    @POST("kandang/kandang_detail.php")
    suspend fun kandang_detail(
        @Field("fc_status") fc_status: String,
        @Field("fn_kavid") fn_kavid: String,
    ): Response<kandang>

    @POST("kandang/type_kandang.php")
    suspend fun type_kandang(): Response<kandang>

    @FormUrlEncoded
    @POST("kandang/filter_kandang.php")
    suspend fun filter_kandang(
        @Field("status") status: String,
        @Field("kandang") kandang: String,
        @Field("kondisi") kondisi: String,
        @Field("batas_min") batas_min: String,
        @Field("batas_max") batas_max: String,
        @Field("kapasitas_min") kapasitas_min: String,
        @Field("kapasitas_max") kapasitas_max: String,
        @Field("harga_min") harga_min: String,
        @Field("harga_max") harga_max: String,
    ): Response<kandang>

    @FormUrlEncoded
    @POST("kandangku/kandangku.php")
    suspend fun kandangku(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<kandangku>


    @FormUrlEncoded
    @POST("kandangku/list_ternak.php")
    suspend fun list_ternak(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_ternak>

    @FormUrlEncoded
    @POST("kandangku/list_perkembangan.php")
    suspend fun list_perkembangan(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_perkembangan>

    @FormUrlEncoded
    @POST("kandangku/list_perawatan.php")
    suspend fun list_perawatan(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_perawatan>

    @FormUrlEncoded
    @POST("kandangku/list_kesehatan.php")
    suspend fun list_kesehatan(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_kesehatan>

    @FormUrlEncoded
    @POST("kandangku/list_kematian.php")
    suspend fun list_kematian(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_kematian>

    @FormUrlEncoded
    @POST("kandangku/list_kelahiran.php")
    suspend fun list_kelahiran(
        @Field("fn_kavid") fn_kavid: String,
    ): Response<list_kelahiran>

    @FormUrlEncoded
    @POST("kandangku/list_display.php")
    suspend fun list_display(
        @Field("fn_kavid") fn_kavid: String,
        @Field("fc_status") fc_status: String,
    ): Response<list_display>

    @FormUrlEncoded
    @POST("kandangku/detail_display.php")
    suspend fun detail_display(
        @Field("fn_kavid") fn_kavid: String,
        @Field("fc_status") fc_status: String,
    ): Response<detail_display>

    @FormUrlEncoded
    @POST("kandangku/detail_kelahiran.php")
    suspend fun detail_kelahiran(
        @Field("fc_nodoc") fc_nodoc: String,
    ): Response<detail_kelahiran>

    @FormUrlEncoded
    @POST("kandangku/detail_kematian.php")
    suspend fun detail_kematian(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_kematian>

    @FormUrlEncoded
    @POST("kandangku/detail_kesehatan.php")
    suspend fun detail_kesehatan(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_kesehatan>

    @FormUrlEncoded
    @POST("kandangku/detail_perawatan.php")
    suspend fun detail_perawatan(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_perawatan>

    @FormUrlEncoded
    @POST("kandangku/detail_perkembangan.php")
    suspend fun detail_perkembangan(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_perkembangan>

    @FormUrlEncoded
    @POST("kandangku/detail_ternak.php")
    suspend fun detail_ternak(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_ternak>



    @FormUrlEncoded
    @POST("kandangku/filter_kelahiran.php")
    suspend fun filter_kelahiran(
        @Field("fn_kavid") fn_kavid: String,
        @Field("akhir") akhir: String,
        @Field("awal") awal: String,
        @Field("kondisi") kondisi: String,
    ): Response<list_kelahiran>

    @FormUrlEncoded
    @POST("kandangku/filter_kematian.php")
    suspend fun filter_kematian(
        @Field("fn_kavid") fn_kavid: String,
        @Field("akhir") akhir: String,
        @Field("awal") awal: String,
        @Field("kondisi") kondisi: String,
    ): Response<list_kematian>


    @FormUrlEncoded
    @POST("kandangku/filter_kesehatan.php")
    suspend fun filter_kesehatan(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_kesehatan>

    @FormUrlEncoded
    @POST("kandangku/filter_perawatan.php")
    suspend fun filter_perawatan(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_perawatan>

    @FormUrlEncoded
    @POST("kandangku/filter_perkembangan.php")
    suspend fun filter_perkembangan(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_perkembangan>

    @FormUrlEncoded
    @POST("titip_angon/list_ternak.php")
    suspend fun list_ternak_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_ternak>

    @FormUrlEncoded
    @POST("titip_angon/list_perkembangan.php")
    suspend fun list_perkembangan_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_perkembangan>

    @FormUrlEncoded
    @POST("titip_angon/list_perawatan.php")
    suspend fun list_perawatan_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_perawatan>

    @FormUrlEncoded
    @POST("titip_angon/list_kesehatan.php")
    suspend fun list_kesehatan_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_kesehatan>

    @FormUrlEncoded
    @POST("titip_angon/list_kematian.php")
    suspend fun list_kematian_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_kematian>

    @FormUrlEncoded
    @POST("titip_angon/list_kelahiran.php")
    suspend fun list_kelahiran_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
    ): Response<list_kelahiran>

    @FormUrlEncoded
    @POST("titip_angon/list_display.php")
    suspend fun list_display_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
        @Field("fc_status") fc_status: String,
    ): Response<list_display>

    @FormUrlEncoded
    @POST("titip_angon/detail_display.php")
    suspend fun detail_display_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
        @Field("fc_status") fc_status: String,
    ): Response<detail_display>

    @FormUrlEncoded
    @POST("titip_angon/detail_kelahiran.php")
    suspend fun detail_kelahiran_titip_angon(
        @Field("fc_nodoc") fc_nodoc: String,
    ): Response<detail_kelahiran>

    @FormUrlEncoded
    @POST("titip_angon/detail_kematian.php")
    suspend fun detail_kematian_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_kematian>

    @FormUrlEncoded
    @POST("titip_angon/detail_kesehatan.php")
    suspend fun detail_kesehatan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_kesehatan>

    @FormUrlEncoded
    @POST("titip_angon/detail_perawatan.php")
    suspend fun detail_perawatan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_perawatan>

    @FormUrlEncoded
    @POST("titip_angon/detail_perkembangan.php")
    suspend fun detail_perkembangan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_perkembangan>

    @FormUrlEncoded
    @POST("titip_angon/detail_ternak.php")
    suspend fun detail_ternak_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
    ): Response<detail_ternak>

    @FormUrlEncoded
    @POST("titip_angon/filter_kelahiran.php")
    suspend fun filter_kelahiran_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
        @Field("akhir") akhir: String,
        @Field("awal") awal: String,
        @Field("kondisi") kondisi: String,
    ): Response<list_kelahiran>

    @FormUrlEncoded
    @POST("titip_angon/filter_kematian.php")
    suspend fun filter_kematian_titip_angon(
        @Field("fn_cusid") fn_cusid: String,
        @Field("akhir") akhir: String,
        @Field("awal") awal: String,
        @Field("kondisi") kondisi: String,
    ): Response<list_kematian>

    @FormUrlEncoded
    @POST("titip_angon/filter_kesehatan.php")
    suspend fun filter_kesehatan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_kesehatan>

    @FormUrlEncoded
    @POST("titip_angon/filter_perawatan.php")
    suspend fun filter_perawatan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_perawatan>

    @FormUrlEncoded
    @POST("titip_angon/filter_perkembangan.php")
    suspend fun filter_perkembangan_titip_angon(
        @Field("fn_sheepid") fn_sheepid: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir: String,
        @Field("kondisi") kondisi: String,
    ): Response<detail_perkembangan>

}
