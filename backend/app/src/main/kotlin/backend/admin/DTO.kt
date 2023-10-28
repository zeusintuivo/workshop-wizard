package backend.admin

import backend.domain.RegistrationState
import backend.user.WorkshopDTO
import kotlinx.serialization.Serializable

@Serializable
data class AdminWorkshopDTO(val title: String, val teacherName: String, val registrations: List<AdminWorkshopRegistration>)

@Serializable
data class AdminWorkshopRegistration(val firstName: String, val lastName: String, val email: String, val state: RegistrationState)

