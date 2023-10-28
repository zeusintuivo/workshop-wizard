package backend.user

import backend.domain.RegistrationState
import backend.domain.User
import backend.domain.WorkShopRegistration
import backend.domain.Workshop
import kotlinx.serialization.*
import java.lang.RuntimeException

class Repository {
    var userMap = mutableMapOf<Int, User>(
        1 to User(1, "John", "Doe", "john.doe@example.com"),
        2 to User(2, "Jane", "Doe", "jane.doe@example.com"),
        3 to User(3, "John", "Smith", "john.smith@example.com",)
    )

    var registrationMap = mutableMapOf<Int, WorkShopRegistration>(
        1 to WorkShopRegistration(1,1, 1, RegistrationState.APPROVED),
        2 to WorkShopRegistration(2, 1, 2, RegistrationState.APPROVED),
        3 to WorkShopRegistration(3, 2, 1, RegistrationState.APPROVED),
        4 to WorkShopRegistration(4,3, 3, RegistrationState.APPROVED)
    )

    var workshopMap = mutableMapOf<Int, Workshop>(
        1 to Workshop(1, "Kotlin", "John Doe"),
        2 to Workshop(2, "Ktor", "Jane Doe"),
        3 to Workshop(3, "Kotlin Multiplatform", "John Doe")
    )

    fun getWorkShopRegistrations(userId: Int) : List<WorkshopDTO> {
        val workshopIds = registrationMap.filter { it.value.userId == userId && it.value.state == RegistrationState.APPROVED }.map { it.value.workshopId }
        return workshopMap.filter { workshopIds.contains(it.key) }.values.map { WorkshopDTO(it.title, it.teacherName) }
    }

    fun addWorkshopRegistrations(userId: Int, workshopId: Int) {
        workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val maxId = registrationMap.keys.max() + 1
        registrationMap.put(maxId, WorkShopRegistration(maxId, userId, workshopId, RegistrationState.PENDING))
    }

    fun cancelWorkshopRegistration(uesrId: Int, workshopId: Int) {
        val registration = registrationMap.filter { it.value.userId == uesrId && it.value.workshopId == workshopId }
            .values.firstOrNull() ?: throw RuntimeException("Workshop registration does not exist")
        registration.state = RegistrationState.CANCELED
    }

}
