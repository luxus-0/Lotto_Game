package pl.lotto.infrastructure.controllers.numbersgenerator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.LottoNumbersDto;

import java.util.Set;

@RestController
class NumbersGeneratorApi {

    private final NumbersGeneratorFacade numbersGeneratorFacade;

    NumbersGeneratorApi(NumbersGeneratorFacade numbersGeneratorFacade) {
        this.numbersGeneratorFacade = numbersGeneratorFacade;
    }

    @GetMapping("/lotto_numbers")
    @ResponseStatus(HttpStatus.OK)
    LottoNumbersDto readLottoNumbers(){
        Set<Integer> randomNumbersLotto = numbersGeneratorFacade.generateLottoNumbers();
        return new LottoNumbersDto(randomNumbersLotto);
    }
}
