package backend.user

import backend.domain.WorkshopRegistrationState
import backend.domain.User
import backend.domain.WorkshopRegistration
import backend.domain.Workshop
import java.lang.RuntimeException

class UserRepository {
    var userMap = mutableMapOf<Int, User>(
        1 to User(1, "John", "Doe", "john.doe@example.com"),
        2 to User(2, "Jane", "Doe", "jane.doe@example.com"),
        3 to User(3, "John", "Smith", "john.smith@example.com",)
    )

    var registrationMap = mutableMapOf<Int, WorkshopRegistration>(
        1 to WorkshopRegistration(1,1, 1, WorkshopRegistrationState.APPROVED),
        2 to WorkshopRegistration(2, 1, 2, WorkshopRegistrationState.APPROVED),
        3 to WorkshopRegistration(3, 2, 1, WorkshopRegistrationState.APPROVED),
        4 to WorkshopRegistration(4,3, 3, WorkshopRegistrationState.APPROVED)
    )

    var workshopMap = mutableMapOf<Int, Workshop>(
        1 to Workshop(1, "Kotlin", "John Doe"),
        2 to Workshop(2, "Ktor", "Jane Doe"),
        3 to Workshop(3, "Kotlin Multiplatform", "John Doe")
    )

    fun getWorkShopRegistrations(userId: Int) : List<WorkshopDTO> {
        val workshopIds = registrationMap.filter { it.value.userId == userId && it.value.state == WorkshopRegistrationState.APPROVED }.map { it.value.workshopId }
        return workshopMap.filter { workshopIds.contains(it.key) }.values.map { WorkshopDTO(it.title, it.teacherName) }
    }

    fun addWorkshopRegistrations(userId: Int, workshopId: Int) {
        workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val maxId = registrationMap.keys.max() + 1
        registrationMap.put(maxId, WorkshopRegistration(maxId, userId, workshopId, WorkshopRegistrationState.PENDING))
    }

    fun cancelWorkshopRegistration(uesrId: Int, workshopId: Int) {
        val registration = registrationMap.filter { it.value.userId == uesrId && it.value.workshopId == workshopId }
            .values.firstOrNull() ?: throw RuntimeException("Workshop registration does not exist")
        registration.state = WorkshopRegistrationState.CANCELED
    }

}
