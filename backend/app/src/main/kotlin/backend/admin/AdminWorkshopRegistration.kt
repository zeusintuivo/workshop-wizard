package backend.admin

import backend.domain.WorkshopRegistrationState
import kotlinx.serialization.Serializable

@Serializable
data class AdminWorkshopRegistration(val firstName: String, val lastName: String, val email: String, val state: WorkshopRegistrationState)