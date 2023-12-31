package christmas.eventplan.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.discount.WeekendEnum;
import christmas.constant.view.Menu;
import christmas.model.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendTest {

    @DisplayName("주말 이벤트 기간에 맞는 할인 금액 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,2023", "2,2023", "8,2023", "9,2023", "15,2023", "16,2023", "22,2023", "23,2023", "29,2023",
            "30,2023"})
    void discountOnOrderInWeekend(String currentDate, int expected) {
        Weekend weekend = new Weekend();

        int actual = weekend.discountOnWeekend(currentDate);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말 이벤트 기간에 해당하지 않는 경우 할인 안됨")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "10", "11", "12", "13", "14", "17", "18", "19", "20", "21", "24",
            "25", "26", "27", "28", "31"})
    void discountOnOrderNotInWeekend(String currentDate) {
        Weekend weekend = new Weekend();

        int actual = weekend.discountOnWeekend(currentDate);

        assertThat(actual).isEqualTo(WeekendEnum.DAY_NOT_EXIST.getDiscount());
    }

    @DisplayName("주말 이벤트 기간에 해당할 때 메인 메뉴 할인 개수 확인")
    @ParameterizedTest
    @ValueSource(ints = {8092})
    void discountOnMainMenu(int expected) {
        Weekend weekend = new Weekend();
        UserInfo userInfo = new UserInfo();
        userInfo.changeVisitDate("1");
        userInfo.addMenuOrder(Menu.MAIN_SEA_PASTA.getMenu(), 1);
        userInfo.addMenuOrder(Menu.MAIN_CHRISTMAS_PASTA.getMenu(), 1);
        userInfo.addMenuOrder(Menu.MAIN_BARBECUE.getMenu(), 2);
        userInfo.addMenuOrder("양송이수프", 5);

        long actual = weekend.discountOnMainMenu(userInfo);

        assertThat(actual).isEqualTo(expected);
    }
}