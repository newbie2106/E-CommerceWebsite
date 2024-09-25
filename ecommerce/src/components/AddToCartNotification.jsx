import React from 'react';

const AddToCartNotification = ({ onGoBack, onViewCart, onClose }) => {
    const handleClickOutside = (event) => {
        if (event.target.id === 'overlay') {
            onClose();
        }
    };

    return (
        <div
            id="overlay"
            className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50"
            onClick={handleClickOutside}
        >
            <div className="bg-white p-6 rounded-lg shadow-lg text-center" onClick={(e) => e.stopPropagation()}>
                <div className="flex justify-center mb-4">
                    <span className="text-green-500 text-3xl">✔️</span>
                </div>
                <p className="mb-4">Bạn đã thêm sản phẩm vào giỏ hàng</p>
                <div className="flex justify-between">
                    <button
                        className="bg-white hover:bg-gray-200 text-gray-700 py-2 px-4 rounded"
                        onClick={onGoBack}
                    >
                        Trở về
                    </button>
                    <button
                        className="bg-blue-600 text-white py-2 px-4 rounded"
                        onClick={onViewCart}
                    >
                        Đến giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AddToCartNotification;
