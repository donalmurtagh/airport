import com.icaviation.EmailSender
import com.icaviation.security.SecurityEventListener
import org.springframework.security.authentication.dao.SystemWideSaltSource

// Place your Spring DSL code here
beans = {

    saltSource(SystemWideSaltSource) {
        systemWideSalt = 'any random string should suffice'
    }

    securityEventListener(SecurityEventListener) {
        messageSource = ref('messageSource')
        springSecurityService = ref('springSecurityService')
    }

    emailSender(EmailSender) {
        mailService = ref('mailService')
        messageSource = ref('messageSource')
        pageRenderer = ref('groovyPageRenderer')
        linkGenerator = ref('grailsLinkGenerator')
    }
}