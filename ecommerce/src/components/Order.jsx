import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import { MyUserContext } from '../App';
import LoadingAnimations from './LoadingAnimations';
import { fetchOrderItems, markOrderAsCancel, markOrderAsDelivered } from '../configs/APIs';
import VNDCurrencyFormat from '../configs/Utils';

const OrderList = ({ username }) => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const user = useContext(MyUserContext);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await fetchOrderItems(user.username);
                setOrders(response);
            } catch (error) {
                console.error("Error fetching orders:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchOrders();
    }, [username]);

    // Hàm gọi API để hủy đơn hàng
    const handleCancelOrder = async (orderId) => {
        try {
            await markOrderAsCancel(orderId);
            alert('Đơn hàng đã được hủy');
            // Cập nhật lại trạng thái đơn hàng sau khi hủy
            setOrders(prevOrders => prevOrders.map(order =>
                order.id === orderId ? { ...order, currentStatus: 'Cancelled' } : order
            ));
        } catch (error) {
            console.error("Error cancelling order:", error);
            alert('Có lỗi xảy ra khi hủy đơn hàng');
        }
    };

    // Hàm gọi API để đánh dấu đơn hàng là đã nhận
    const handleMarkAsDelivered = async (orderId) => {
        try {
            await markOrderAsDelivered(orderId); 
            alert('Đơn hàng đã được đánh dấu là đã nhận');
            // Cập nhật lại trạng thái đơn hàng sau khi đánh dấu là Delivered
            setOrders(prevOrders => prevOrders.map(order =>
                order.id === orderId ? { ...order, currentStatus: 'Delivered' } : order
            ));
        } catch (error) {
            console.error("Error marking order as delivered:", error);
            alert('Có lỗi xảy ra khi đánh dấu đơn hàng');
        }
    };

    if (loading) {
        return <LoadingAnimations />;
    }

    return (
        <div className="container mx-auto mt-5 max-w-5xl">
            <h2 className="text-center text-2xl font-semibold text-info">Quản lý Đơn hàng</h2>
            <div className="order-list">
                {orders.length === 0 ? (
                    <p className="text-center text-gray-600">Không có đơn hàng nào.</p>
                ) : (
                    orders.map((order) => (
                        <div key={order.id} className="order-item border p-4 mb-4 shadow-md rounded-md">
                            <div className="order-header flex justify-between">
                                <div>
                                    <strong>Ngày đặt hàng:</strong>
                                    <span className="ml-2">{new Date(order.createdDate).toLocaleString()}</span>
                                </div>
                                <div>
                                    <span className="text-blue-600 d-inline-block mt-2">{order.currentStatus} | </span>
                                    <span className={`ml-2 ${order.isPaid ? 'text-green-600' : 'text-red-600'}`}>
                                        {order.isPaid ? 'Đã thanh toán' : 'Chưa thanh toán'}
                                    </span>
                                </div>
                            </div>

                            <div className="order-details mt-3">
                                {order.orderDetails.map((detail) => (
                                    <div key={detail.productId} className="product-item flex items-center border-b py-2">
                                        <img src={detail.productImage} alt={detail.productName} width="100" height="80" className="mr-3" />
                                        <div>
                                            <h5 className="text-lg font-medium">{detail.productName}</h5>
                                            <p>Giá: <span className="text-red-600">{VNDCurrencyFormat.format(detail.unitPrice)}</span></p>
                                            <p>Số lượng: {detail.quantity}</p>
                                        </div>
                                    </div>
                                ))}
                            </div>

                            <hr className="my-4" />

                            <div className="flex justify-between items-center">
                                <div className="text-right">
                                    <strong>Tổng tiền:</strong>
                                    <span className="text-blue-600 ml-2">{VNDCurrencyFormat.format(order.totalAmount)}</span>
                                </div>

                                {/* Hiển thị nút dựa trên trạng thái đơn hàng */}
                                {order.currentStatus === 'Pending' && (
                                    <button
                                        className="bg-red-500 text-white px-4 py-2 rounded-md"
                                        onClick={() => handleCancelOrder(order.id)}
                                    >
                                        Hủy đơn hàng
                                    </button>
                                )}
                                {order.currentStatus === 'Shipped' && (
                                    <button
                                        className="bg-green-500 text-white px-4 py-2 rounded-md"
                                        onClick={() => handleMarkAsDelivered(order.id)}
                                    >
                                        Đã nhận hàng
                                    </button>
                                )}
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default OrderList;
