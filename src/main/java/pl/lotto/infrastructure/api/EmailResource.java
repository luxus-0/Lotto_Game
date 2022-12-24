package pl.lotto.infrastructure.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
class EmailResource {

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    String sendEmail() {
        return "";
    }
}
