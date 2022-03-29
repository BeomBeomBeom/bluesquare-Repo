package msateam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;



 @RestController
 public class HallController {

        @Autowired
        HallRepository hallRepository;


@RequestMapping(value = "/check/checkReservation",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public boolean checkReservation(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        System.out.println("##### /hall/checkReservation  called #####");

        // Parameter로 받은 SeatId 추출
        long seatId = Long.valueOf(request.getParameter("seatId"));
        System.out.println("######################## checkReservation seatId : " + seatId);

        // SeatId 데이터 조회
        Optional<Hall> res = hallRepository.findById(seatId);
        Hall hall = res.get(); // 조회한 Hall seat 데이터
        System.out.println("######################## checkReservation hall.getStatus() : " + hall.getStatus());

        // room의 상태가 available이면 true
        boolean result = false;
        if(hall.getStatus().equals("available")) {
                result = true;
        } 

        System.out.println("######################## checkReservation Return : " + result);
        return result;
        }
}