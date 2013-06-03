import com.icaviation.EmailSender
import org.springframework.security.authentication.dao.SystemWideSaltSource

// Place your Spring DSL code here
beans = {

    saltSource(SystemWideSaltSource) {
        systemWideSalt = 'man utd 1 - 6 man city'
    }

    emailSender(EmailSender) {
        mailService = ref('mailService')
        messageSource = ref('messageSource')
        pageRenderer = ref('groovyPageRenderer')
        linkGenerator = ref('grailsLinkGenerator')
    }
}