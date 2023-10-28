package backend.admin

import backend.domain.RegistrationState
import backend.domain.User
import backend.domain.WorkShopRegistration
import backend.domain.Workshop

class AdminRepository {
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
    fun getWorkshops(): List<AdminWorkshopDTO> {
        return workshopMap.map { workshop ->
            val registrations = registrationMap.filter { it.value.workshopId == workshop.key }
                .map { it.value }
                .map { AdminWorkshopRegistration(
                    userMap[it.userId]!!.firstName,
                    userMap[it.userId]!!.lastName,
                    userMap[it.userId]!!.email,
                    it.state)
                }
            AdminWorkshopDTO(workshop.value.title, workshop.value.teacherName, registrations)
        }
    }

    fun getWorkshopRegistrations(workshopId: Int): AdminWorkshopDTO {
        val workshop = workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val registrations = registrationMap.filter { it.value.workshopId == workshop.id }
            .map { it.value }
            .map {
                AdminWorkshopRegistration(
                    userMap[it.userId]!!.firstName,
                    userMap[it.userId]!!.lastName,
                    userMap[it.userId]!!.email,
                    it.state
                )
            }
        return AdminWorkshopDTO(workshop.title, workshop.teacherName, registrations)
    }
}
