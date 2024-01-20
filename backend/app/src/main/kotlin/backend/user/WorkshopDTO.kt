package backend.user

import kotlinx.serialization.Serializable

@Serializable
data class WorkshopDTO(val title: String, val teacherName: String)


