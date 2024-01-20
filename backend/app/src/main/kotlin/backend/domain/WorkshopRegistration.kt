package backend.domain

class WorkshopRegistration(
    val id: Int,
    val userId: Int,
    val workshopId: Int,
    var state: WorkshopRegistrationState = WorkshopRegistrationState.PENDING,
)
