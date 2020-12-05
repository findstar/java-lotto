package lotto.automatic.view;

import lotto.automatic.domain.Lotto;
import lotto.automatic.domain.LottoRank;
import lotto.automatic.domain.LottoResult;
import lotto.automatic.domain.Lottos;

import java.util.stream.Stream;

public class OutputView {

    public void printLottoList(Lottos lottos) {

        System.out.printf("%d개를 구매했습니다.\n", lottos.size());

        lottos.apply(Lotto::toString)
                .forEach(System.out::println);

        System.out.println();
    }

    public void printLottoResult(LottoResult result) {

        System.out.println("당첨 통계");
        System.out.println("---------");

        Stream.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD)
                .forEach(r -> printRankCount(r, result.getRankCount(r)));
        printBonusRank(result.getRankCount(LottoRank.SECOND));
        printRankCount(LottoRank.FIRST, result.getRankCount(LottoRank.FIRST));

        System.out.printf("총 수익률은 %.2f입니다.\n", result.calculateEarningRate());
    }

    private void printBonusRank(long rankCount) {
        System.out.printf("5개 일치, 보너스 볼 일치 (%d)원 - %d개\n", LottoRank.SECOND.getEarningMoney(), rankCount);
    }

    private void printRankCount(LottoRank rank, long count) {

        System.out.printf("%d개 일치 (%d)원 - %d개\n", rank.getMatchCount(), rank.getEarningMoney(), count);
    }
}
