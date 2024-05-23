import { GetTossSuccessParams, getTossSuccess } from "@/apis/apiPayment";
import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

const TossSuccess = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const orderId = searchParams.get("orderId");
  const paymentKey = searchParams.get("paymentKey");
  const amount = searchParams.get("amount");

  useEffect(() => {
    if (orderId && paymentKey && amount) {
      const params: GetTossSuccessParams = {
        orderId: orderId,
        paymentKey: paymentKey,
        amount: amount,
      };
      getTossSuccess(params);

      navigate("/mypage/payment");
    } else {
      alert("결제 실패");
    }
  }, []);

  return <div>성공했습니다.</div>;
};

export default TossSuccess;
