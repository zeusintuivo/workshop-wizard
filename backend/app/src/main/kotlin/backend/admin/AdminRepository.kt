package backend.admin

import backend.domain.WorkshopRegistrationState
import backend.domain.User
import backend.domain.WorkshopRegistration
import backend.domain.Workshop

class AdminRepository {
    val userMap = mutableMapOf(
        1 to User(1, "John", "Doe", "john.doe@example.com"),
        2 to User(2, "Jane", "Doe", "jane.doe@example.com"),
        3 to User(3, "John", "Smith", "john.smith@example.com",)
    )

    val registrationMap = mutableMapOf(
        1 to WorkshopRegistration(1,1, 1, WorkshopRegistrationState.APPROVED),
        2 to WorkshopRegistration(2, 1, 2, WorkshopRegistrationState.APPROVED),
        3 to WorkshopRegistration(3, 2, 1, WorkshopRegistrationState.APPROVED),
        4 to WorkshopRegistration(4,3, 3, WorkshopRegistrationState.APPROVED)
    )

    val workshopMap = mutableMapOf(
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
