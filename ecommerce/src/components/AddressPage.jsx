// import React, { useState, useEffect } from 'react';
// import { Link } from 'react-router-dom';
// import { FaPlus, FaMapMarkerAlt, FaEdit, FaTrash } from 'react-icons/fa';
// import { fetchShippingAddresses, deleteShippingAddress } from '../configs/APIs'; // Giả sử bạn có API lấy và xóa địa chỉ

// const AddressPage = () => {
//   const [addresses, setAddresses] = useState([]);

//   // Load addresses from API
//   useEffect(() => {
//     fetchShippingAddresses()
//       .then((res) => {
//         setAddresses(res.data); // Giả sử API trả về dữ liệu dưới dạng `res.data`
//       })
//       .catch((err) => {
//         console.error('Error fetching addresses:', err);
//       });
//   }, []);

//   // Hàm xóa địa chỉ
//   const handleDeleteAddress = (id) => {
//     if (window.confirm('Bạn có chắc chắn muốn xóa địa chỉ này?')) {
//       deleteShippingAddress(id).then(() => {
//         // Sau khi xóa, load lại danh sách địa chỉ
//         setAddresses((prevAddresses) =>
//           prevAddresses.filter((address) => address.id !== id)
//         );
//       });
//     }
//   };

//   return (
//     <div className="container mx-auto my-10">
//       <div className="bg-white shadow-md rounded-lg p-6">
//         {/* Header của trang */}
//         <div className="flex justify-between items-center border-b pb-4 mb-4">
//           <h1 className="text-2xl font-semibold text-gray-800">Địa chỉ của tôi</h1>
//           <Link
//             to="/add-address" // Giả sử đây là đường dẫn để thêm địa chỉ mới
//             className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
//           >
//             <FaPlus className="inline-block mr-2" /> Thêm địa chỉ
//           </Link>
//         </div>

//         {/* Danh sách địa chỉ */}
//         {addresses.length > 0 ? (
//           <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
//             {addresses.map((address) => (
//               <div
//                 key={address.id}
//                 className="p-4 bg-gray-100 rounded-lg shadow-md relative"
//               >
//                 <div className="flex justify-between items-start mb-2">
//                   <h2 className="text-lg font-bold text-gray-800">
//                     <FaMapMarkerAlt className="inline-block text-blue-500 mr-2" />
//                     {address.full_name}
//                   </h2>
//                   {address.is_default && (
//                     <span className="bg-blue-500 text-white px-2 py-1 text-xs rounded">
//                       Mặc định
//                     </span>
//                   )}
//                 </div>
//                 <p className="text-gray-600">{address.address}</p>
//                 <p className="text-gray-600">{address.phone_number}</p>

//                 <div className="flex justify-between items-center mt-4">
//                   <Link
//                     to={`/edit-address/${address.id}`}
//                     className="text-blue-500 hover:underline flex items-center"
//                   >
//                     <FaEdit className="mr-1" /> Sửa
//                   </Link>
//                   <button
//                     onClick={() => handleDeleteAddress(address.id)}
//                     className="text-red-500 hover:underline flex items-center"
//                   >
//                     <FaTrash className="mr-1" /> Xóa
//                   </button>
//                 </div>
//               </div>
//             ))}
//           </div>
//         ) : (
//           <p className="text-gray-600">Bạn chưa có địa chỉ nào.</p>
//         )}
//       </div>
//     </div>
//   );
// };

// export default AddressPage;
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaPlus, FaMapMarkerAlt, FaEdit, FaTrash } from 'react-icons/fa';
import { getAllShippingAddresses, getCurrentUser } from '../configs/APIs';

const AddressPage = () => {
    // Dữ liệu ảo để mô phỏng các địa chỉ đã lưu
    const [addresses, setAddresses] = useState([]);
    const [user, setUser] = useState({});

    useEffect(() => {
        const fetchCurrentUser = async () => {
            try {
                const res = await getCurrentUser();
                if (res && res.data) {
                    setUser(res.data);
                }
            } catch (error) {
                console.error("Failed to fetch user data:", error);
            } finally {
                setLoadingUser(false);
            }
        };
        fetchCurrentUser();
    }, []);



    useEffect(() => {
        getAllShippingAddresses(user.username)
            .then((res) => {
                setAddresses(res.data); // Giả sử API trả về dữ liệu dưới dạng `res.data`
            })
            .catch((err) => {
                console.error('Error fetching addresses:', err);
            });
    }, []);


    // Hàm thiết lập địa chỉ mặc định
    const handleSetDefault = (id) => {
        const updatedAddresses = addresses.map((address) =>
            address.id === id
                ? { ...address, is_default: true }
                : { ...address, is_default: false }
        );
        setAddresses(updatedAddresses);
    };

    // Hàm mô phỏng việc xóa địa chỉ (ẩn nếu là địa chỉ mặc định)
    const handleDeleteAddress = (id) => {
        const confirmDelete = window.confirm('Bạn có chắc chắn muốn xóa địa chỉ này?');
        if (confirmDelete) {
            setAddresses(addresses.filter((address) => address.id !== id));
        }
    };

    // Sắp xếp địa chỉ để địa chỉ mặc định luôn hiển thị ở đầu
    const sortedAddresses = [...addresses].sort((a, b) => b.is_default - a.is_default);

    return (
        <div className="container mx-auto my-10">
            <div className="bg-white shadow-md rounded-lg p-6">
                {/* Header của trang */}
                <div className="flex justify-between items-center border-b pb-4 mb-4">
                    <h1 className="text-2xl font-semibold text-gray-800">Địa chỉ của tôi</h1>
                    <Link
                        to="/add-address" // Giả sử đây là đường dẫn để thêm địa chỉ mới
                        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
                    >
                        <FaPlus className="inline-block mr-2" /> Thêm địa chỉ
                    </Link>
                </div>

                {/* Danh sách địa chỉ */}
                {sortedAddresses.length > 0 ? (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {sortedAddresses.map((address) => (
                            <div
                                key={address.id}
                                className="p-4 bg-gray-100 rounded-lg shadow-md relative"
                            >
                                <div className="flex justify-between items-start mb-2">
                                    <h2 className="text-lg font-bold text-gray-800">
                                        <FaMapMarkerAlt className="inline-block text-blue-500 mr-2" />
                                        {address.full_name}
                                    </h2>
                                    {!address.is_default && (
                                        <button
                                            onClick={() => handleSetDefault(address.id)}
                                            className="text-sm text-blue-500 hover:underline"
                                        >
                                            Thiết lập mặc định
                                        </button>
                                    )}
                                    {address.is_default && (
                                        <span className="bg-blue-500 text-white px-2 py-1 text-xs rounded">
                                            Mặc định
                                        </span>
                                    )}
                                </div>
                                <p className="text-gray-600">{address.address}</p>
                                <p className="text-gray-600">{address.phone_number}</p>

                                <div className="flex justify-between items-center mt-4">
                                    <Link
                                        to={`/edit-address/${address.id}`}
                                        className="text-blue-500 hover:underline flex items-center"
                                    >
                                        <FaEdit className="mr-1" /> Sửa
                                    </Link>
                                    {!address.is_default && (
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
                )}
            </div>
        </div>
    );
};

export default AddressPage;
