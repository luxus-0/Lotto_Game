package pl.lotto.resultchecker.dto;

import java.util.Set;

public record ResultsLotto(Set<Integer> resultNumbers, String message) {
}
