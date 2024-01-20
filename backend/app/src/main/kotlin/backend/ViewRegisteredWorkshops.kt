package backend

import backend.domain.WorkshopRegistration
import backend.user.UserRepository

class ViewRegisteredWorkshops(private val userRepository: UserRepository) {
    fun viewRegisteredWorkshops(userId: Int): List<WorkshopRegistration> {
        val workshops = userRepository.getWorkShopRegistrations(userId)
        // Legg til funksjonalitet for å vise hvilke workshops brukeren har venteliste på
        return workshops
    }
}
