import React, { useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { FaPlus, FaMapMarkerAlt, FaEdit, FaTrash } from 'react-icons/fa';
import { getAllShippingAddresses, updateDefaultAddress } from '../configs/APIs';
import { MyUserContext } from '../App';
import LoadingAnimations from '../components/LoadingAnimations'; // Giả sử bạn đã tạo component này

const AddressPage = () => {
    const [addresses, setAddresses] = useState([]);
    const user = useContext(MyUserContext);
    const [loading, setLoading] = useState(false); // Thêm state loading

    useEffect(() => {
        const fetchAddresses = async () => {
            setLoading(true); // Bắt đầu loading
            try {
                const res = await getAllShippingAddresses(user.username);
                setAddresses(res.data);
            } catch (err) {
                console.error('Error fetching addresses:', err);
            } finally {
                setLoading(false); // Kết thúc loading
            }
        };

        fetchAddresses();
    }, [user.username]); // Thêm user.username vào dependency để lấy lại địa chỉ khi user thay đổi

    const handleSetDefault = async (id) => {
        setLoading(true); // Bắt đầu loading
        await updateDefaultAddress(id, user.username);
        const res = await getAllShippingAddresses(user.username);
        setAddresses(res.data);
        setLoading(false); // Kết thúc loading
    };

    const handleDeleteAddress = (id) => {
        const confirmDelete = window.confirm('Bạn có chắc chắn muốn xóa địa chỉ này?');
        if (confirmDelete) {
            setAddresses(addresses.filter((address) => address.id !== id));
        }
    };

    const sortedAddresses = [...addresses].sort((a, b) => b.isDefault - a.isDefault);

    return (
        <div className="container mx-auto my-10">
            <div className="bg-white shadow-md rounded-lg p-6">
                <div className="flex justify-between items-center border-b pb-4 mb-4">
                    <h1 className="text-2xl font-semibold text-gray-800">Địa chỉ của tôi</h1>
                    <Link
                        to="/add-address"
                        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
                    >
                        <FaPlus className="inline-block mr-2" /> Thêm địa chỉ
                    </Link>
                </div>

                {loading ? ( // Kiểm tra trạng thái loading
                    <LoadingAnimations />
                ) : (
                    sortedAddresses.length > 0 ? (
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                            {sortedAddresses.map((address) => (
                                <div
                                    key={address.id}
                                    className="p-4 bg-gray-100 rounded-lg shadow-md relative"
                                >
                                    <div className="flex justify-between items-start mb-2">
                                        <h2 className="text-lg font-bold text-gray-800">
                                            <FaMapMarkerAlt className="inline-block text-blue-500 mr-2" />
                                            {address.fullName}
                                        </h2>
                                        {!address.isDefault && (
                                            <button
                                                onClick={() => handleSetDefault(address.id)}
                                                className="text-sm text-blue-500 hover:underline"
                                            >
                                                Thiết lập mặc định
                                            </button>
                                        )}
                                        {address.isDefault && (
                                            <span className="bg-blue-500 text-white px-2 py-1 text-xs rounded">
                                                Mặc định
                                            </span>
                                        )}
                                    </div>
                                    <p className="text-gray-600"><span className='font-bold'>Địa chỉ: </span>{address.address}, {address.wardCode.fullName},
                                        {address.districtCode.fullName}, {address.provinceCode.fullName}</p>
                                    <p className="text-gray-600"><span className='font-bold'>Số điện thoại: </span> {address.phone}</p>

                                    <div className="flex justify-between items-center mt-4">
                                        <Link
                                            to={`/edit-address/${address.id}`}
                                            className="text-blue-500 hover:underline flex items-center"
                                        >
                                            <FaEdit className="mr-1" /> Sửa
                                        </Link>
                                        {!address.isDefault && (
                                            <button
                                                onClick={() => handleDeleteAddress(address.id)}
                                                className="text-red-500 hover:underline flex items-center"
                                            >
                                                <FaTrash className="mr-1" /> Xóa
                                            </button>
                                        )}
                                    </div>
                                </div>
                            ))}
                        </div>
                    ) : (
                        <p className="text-gray-600">Bạn chưa có địa chỉ nào.</p>
                    )
                )}
            </div>
        </div>
    );
};

export default AddressPage;
