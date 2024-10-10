
import { useEffect, useRef, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom"; // để lấy params từ URL
import { createSaleOrder } from "../configs/APIs";

const PaymentReturn = () => {
    const [searchParams] = useSearchParams();
    const nav = useNavigate();
    const checkAddedRef = useRef(false);
    console.log("C" + checkAddedRef.current);
    useEffect(() => {
        const processVNPayReturn = async () => { // Hàm async bên trong useEffect
            try {
                const vnp_TransactionStatus = searchParams.get("vnp_TransactionStatus");
                const vnp_Amount = searchParams.get("vnp_Amount");

                if (vnp_TransactionStatus === "00" && !checkAddedRef.current) {
                    const saleOrderRequest = {
                        username: localStorage.getItem("username"),
                        totalAmount: vnp_Amount / 100,
                        isPaid: true,
                        note: localStorage.getItem("note"),
                        branchId: 1,
                        shippingAdressId: localStorage.getItem("selectedAddressId"),
                        carrierId: localStorage.getItem("carrierId"),
                        orderDetails: JSON.parse(localStorage.getItem("items")),
                    };
                    checkAddedRef.current = true;
                    const response = await createSaleOrder(saleOrderRequest);
                    alert("Đơn hàng đã được tạo thành công: " + response);
                    nav("/");
                } else {
                    alert("Thanh toán thất bại. Mã lỗi: " + checkAddedRef.current);
                    
                }
            } catch (error) {
                alert("Đã có lỗi xảy ra khi tạo đơn hàng: " + error.message);
                // Điều hướng về trang chủ nếu có lỗi
            }
        };

        processVNPayReturn();
    }, []);

    return <div>Đang xử lý kết quả thanh toán...</div>;
};

export default PaymentReturn;
