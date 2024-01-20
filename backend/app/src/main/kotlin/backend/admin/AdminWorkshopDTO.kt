package backend.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminWorkshopDTO(
    val title: String,
    val teacherName: String,
    val registrations: List<AdminWorkshopRegistration>,
)
