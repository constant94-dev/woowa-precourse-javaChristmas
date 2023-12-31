package christmas.eventplan.view;

import static christmas.constant.view.EventPlanner.PLANNER_HEADER;
import static christmas.constant.view.EventPlanner.PLANNER_ORDER_NUMBER;
import static christmas.constant.view.EventPlanner.PLANNER_VISIT_DATE;
import static christmas.constant.view.Flag.ORDER_MENU_NUMBER_BREAK;
import static christmas.constant.view.Flag.ORDER_MENU_NUMBER_CONTINUE;
import static christmas.constant.view.Flag.VISIT_DATE_BREAK;
import static christmas.constant.view.Flag.VISIT_DATE_CONTINUE;
import static christmas.utils.menu.Ordered.createMenuOrders;
import static christmas.utils.validate.InputViewValidator.validateMenuToExist;
import static christmas.utils.validate.InputViewValidator.validateMore1AndLess31;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.OrderInfo;
import christmas.model.UserInfo;

public class InputView {
    public String helloWooTeco() {
        System.out.println(PLANNER_HEADER.getPlanner());
        return PLANNER_HEADER.getPlanner();
    }

    public void visitToDate(UserInfo userInfo) {
        String flag = VISIT_DATE_CONTINUE.getMessage();
        while (!flag.equals(VISIT_DATE_BREAK.getMessage())) {
            try {
                System.out.println(PLANNER_VISIT_DATE.getPlanner());
                String visitDate = Console.readLine();

                if (validateMore1AndLess31(visitDate)) {
                    userInfo.changeVisitDate(visitDate);
                    flag = VISIT_DATE_BREAK.getMessage();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void orderToMenuAndCount(UserInfo userInfo, OrderInfo orderInfo) {
        String flag = ORDER_MENU_NUMBER_CONTINUE.getMessage();
        while (!flag.equals(ORDER_MENU_NUMBER_BREAK.getMessage())) {
            try {
                System.out.println(PLANNER_ORDER_NUMBER.getPlanner());
                String order = Console.readLine();

                validateMenuToExist(order, orderInfo);

                if (createMenuOrders(userInfo, orderInfo)) {
                    flag = ORDER_MENU_NUMBER_BREAK.getMessage();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
