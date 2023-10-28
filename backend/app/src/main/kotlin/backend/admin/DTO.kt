package backend.admin

import backend.user.RegistrationState
import backend.user.WorkshopDTO

data class WorkshopRegistrationDTO(val state: RegistrationState, val workshop: WorkshopDTO)
