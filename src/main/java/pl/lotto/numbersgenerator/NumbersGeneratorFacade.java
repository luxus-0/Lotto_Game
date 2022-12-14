package pl.lotto.numbersgenerator;

import org.springframework.stereotype.Service;
import pl.lotto.numbersgenerator.dto.LottoNumbersDto;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.lotto.numbersgenerator.WinningNumbersMessageProvider.winningNumbersNotFound;

@Service
public class NumbersGeneratorFacade {

    private static final Integer MIN_NUMBER = 1;
    private static final Integer MAX_NUMBER = 99;

    private final NumbersGeneratorValidator numbersGeneratorValidator;
    private final NumbersGeneratorRepository numbersGeneratorRepository;

    public NumbersGeneratorFacade(NumbersGeneratorValidator numbersGeneratorValidator, NumbersGeneratorRepository numbersGeneratorRepository) {
        this.numbersGeneratorValidator = numbersGeneratorValidator;
        this.numbersGeneratorRepository = numbersGeneratorRepository;
    }

    public Set<Integer> generateLottoNumbers() {
        return IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .map(randomNumbers -> new Random().nextInt())
                .boxed()
                .collect(Collectors.toSet());
    }

    public WinningNumbersDto showWinnerNumbers() {
        Set<Integer> lottoNumbers = generateLottoNumbers();
        if (numbersGeneratorValidator.valid(lottoNumbers)) {
            for (Integer lottoNumber : lottoNumbers) {
                return new WinningNumbersDto(Set.of(lottoNumber));
            }
        }
        winningNumbersNotFound();
        return new WinningNumbersDto(Set.of(0));
    }

    public LottoNumbersDto findNumbersGeneratorById(UUID uuid) {
        return numbersGeneratorRepository.findById(uuid)
                .map(NumbersGeneratorMapper::toDto)
                .orElseThrow(IllegalArgumentException::new);
    }

    public LottoNumbersDto findNumbersGenerator() {
        return numbersGeneratorRepository.findAll()
                .stream()
                .map(NumbersGeneratorMapper::toDto)
                .findAny()
                .orElse(null);
    }

    public NumbersGenerator createNumbersGenerator(NumbersGenerator numbersGenerator) {
        return numbersGeneratorRepository.save(numbersGenerator);
    }
}
