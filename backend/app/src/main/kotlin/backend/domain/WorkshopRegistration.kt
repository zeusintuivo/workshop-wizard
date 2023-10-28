package backend.domain

class WorkShopRegistration(val id: Int, val userId: Int, val workshopId: Int, var state: RegistrationState = RegistrationState.PENDING)
