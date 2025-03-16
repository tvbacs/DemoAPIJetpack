// Đầu tiên, tạo các data class để phản ánh cấu trúc JSON
// PostModel.kt
package com.example.demogetapi.data.model
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val post_id: String,
    val title: String,
    val content: String,
    val category_id: String,
    val user_id: String,
    val type: String,
    val group_id: String?,
    val time_short: String?,
    val createdAt: String,
    val updatedAt: String,
    val category: Category,
    val user: User,
    val media: List<Media> =emptyList()
)

@Serializable
data class Category(
    val category_id: String,
    val category_name: String,
    val createdAt: String?,
    val updatedAt: String?
)

@Serializable
data class User(
    val user_id: String,
    val fullname: String,
    val email: String,
    val username: String,
    val password: String,
    val phonenumber: String,
    val gender: String,
    val country: String?,
    val nickname: String,
    val email_verified_at: String?,
    val avatar: String?,
    val birthday: String,
    val type: String,
    val otp: String?,
    val otp_expires: String,
    val status: String,
    val level: Int,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class Media(
    val id: Int,
    val post_id: String,
    val file_name: String,
    val file_path: FilePath,
    val media_type: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class FilePath(
    val type: String,
    val data: List<Int>
)