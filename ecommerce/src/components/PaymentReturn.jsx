
// import { useEffect, useRef, useState } from "react";
// import { useNavigate, useSearchParams } from "react-router-dom"; // để lấy params từ URL
// import { createSaleOrder } from "../configs/APIs";

// const PaymentReturn = () => {
//     const [searchParams] = useSearchParams();
//     const nav = useNavigate();
//     const checkAddedRef = useRef(false);
//     console.log("C" + checkAddedRef.current);
//     useEffect(() => {
//         const processVNPayReturn = async () => { // Hàm async bên trong useEffect
//             try {
//                 const vnp_TransactionStatus = searchParams.get("vnp_TransactionStatus");
//                 const vnp_Amount = searchParams.get("vnp_Amount");

//                 if (vnp_TransactionStatus === "00" && !checkAddedRef.current) {
//                     const saleOrderRequest = {
//                         username: localStorage.getItem("username"),
//                         totalAmount: vnp_Amount / 100,
//                         isPaid: true,
//                         note: localStorage.getItem("note"),
//                         branchId: 1,
//                         shippingAdressId: localStorage.getItem("selectedAddressId"),
//                         carrierId: localStorage.getItem("carrierId"),
//                         orderDetails: JSON.parse(localStorage.getItem("items")),
//                     };
//                     checkAddedRef.current = true;
//                     const response = await createSaleOrder(saleOrderRequest);
//                     alert("Đơn hàng đã được tạo thành công: " + response);
//                     nav("/");
//                 } else {
//                     alert("Thanh toán thất bại. Mã lỗi: " + checkAddedRef.current);

//                 }
//             } catch (error) {
//                 alert("Đã có lỗi xảy ra khi tạo đơn hàng: " + error.message);
//                 // Điều hướng về trang chủ nếu có lỗi
//             }
//         };

//         processVNPayReturn();
//     }, []);

//     return <div>Đang xử lý kết quả thanh toán...</div>;
// };

// export default PaymentReturn;


import { useEffect, useRef, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { createSaleOrder } from "../configs/APIs";
import LoadingAnimations from "./LoadingAnimations";

const PaymentReturn = () => {
    const [searchParams] = useSearchParams();
    const nav = useNavigate();
    const checkAddedRef = useRef(false);
    const [isProcessing, setIsProcessing] = useState(false);
    const [isLoading, setIsLoading] = useState(true); // Trạng thái loading

    useEffect(() => {
        const processVNPayReturn = async () => {
            if (isProcessing) return;

            setIsProcessing(true); 
            try {
                const vnp_TransactionStatus = searchParams.get("vnp_TransactionStatus");
                const vnp_Amount = searchParams.get("vnp_Amount")
                if (!checkAddedRef.current) {
                    const saleOrderRequest = {
                        username: localStorage.getItem("username"),
                        totalAmount: localStorage.getItem("totalAmount"),
                        isPaid: true,
                        note: localStorage.getItem("note"),
                        branchId: 1,
                        shippingAdressId: localStorage.getItem("selectedAddressId"),
                        carrierId: localStorage.getItem("carrierId"),
                        orderDetails: JSON.parse(localStorage.getItem("items")),
                    };
                    console.log(vnp_TransactionStatus)

                    console.log(saleOrderRequest)
                    checkAddedRef.current = true;

                    const response = await createSaleOrder(saleOrderRequest);
                    alert("Đơn hàng đã được tạo thành công: " + response);

                    // Xóa thông tin khỏi localStorage sau khi đã xử lý
                    localStorage.removeItem("username");
                    localStorage.removeItem("selectedAddressId");
                    localStorage.removeItem("carrierId");
                    localStorage.removeItem("items");
                    localStorage.removeItem("note");
                    localStorage.removeItem("totalAmount");
                    nav("/");
                } else {
                    alert("Thanh toán thất bại. Mã lỗi: " + response);
                }
            } catch (error) {
                alert("Đã có lỗi xảy ra khi tạo đơn hàng: " + error.message);
            } finally {
                setIsProcessing(false);
                setIsLoading(false); // Ngừng hiển thị hiệu ứng loading khi đã xử lý xong
            }
        };

        processVNPayReturn();
    }, [isProcessing, searchParams, nav]);

    // Hiển thị LoadingAnimation nếu đang xử lý
    if (isLoading) {
        return <LoadingAnimations />;
    }

    return <div>Đơn hàng của bạn đang được xử lý...</div>;
};

export default PaymentReturn;


// import { useEffect, useRef, useState } from "react";
// import { useNavigate, useSearchParams } from "react-router-dom";
// import { createSaleOrder } from "../configs/APIs";

// const PaymentReturn = () => {
//     const [searchParams] = useSearchParams();
//     const nav = useNavigate();
//     const checkAddedRef = useRef(false);

//     useEffect(() => {
//         const processVNPayReturn = async () => {
//             const saleOrderRequest = {
//                 username: localStorage.getItem("username"),
//                 totalAmount: localStorage.getItem("totalAmount"),
//                 isPaid: true,
//                 note: localStorage.getItem("note"),
//                 branchId: 1,
//                 shippingAdressId: localStorage.getItem("selectedAddressId"),
//                 carrierId: localStorage.getItem("carrierId"),
//                 orderDetails: JSON.parse(localStorage.getItem("items")),
//             };

//             const response = await createSaleOrder(saleOrderRequest);
//             alert("Đơn hàng đã được tạo thành công: " + response);

//             // Xóa thông tin khỏi localStorage sau khi đã xử lý
//             localStorage.removeItem("username");
//             localStorage.removeItem("selectedAddressId");
//             localStorage.removeItem("carrierId");
//             localStorage.removeItem("items");
//             localStorage.removeItem("note");
//             localStorage.removeItem("totalAmount");


//             nav("/");
//         };

//         processVNPayReturn();
//     }, [, searchParams, nav]); // Thêm các dependencies cần thiết

//     return <div>Đang xử lý kết quả thanh toán...</div>;
// };

// export default PaymentReturn;
