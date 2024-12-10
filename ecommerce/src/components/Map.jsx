// import React, { useState, useEffect } from 'react';
// import { GoogleMap, Marker, LoadScript } from '@react-google-maps/api';

// const Map = () => {
//     const [lat, setLat] = useState(0);
//     const [lng, setLng] = useState(0);
//     const [mapLoaded, setMapLoaded] = useState(false);

//     // Sử dụng useEffect để tự động lấy vị trí hiện tại khi tải trang
//     useEffect(() => {
//         if (navigator.geolocation) {
//             navigator.geolocation.getCurrentPosition((position) => {
//                 const { latitude, longitude } = position.coords;
//                 setLat(latitude);
//                 setLng(longitude);
//             }, (error) => {
//                 console.error("Error getting location:", error);
//             });
//         } else {
//             console.error("Geolocation is not supported by this browser.");
//         }
//     }, []);

//     return (
//         <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
//             <div className="w-full max-w-4xl bg-white shadow-lg rounded-lg p-6">
//                 <h2 className="text-2xl font-bold mb-4 text-center">Bản Đồ Hiện Tại</h2>
//                 <div className="w-full h-96 rounded-lg overflow-hidden">
//                     <LoadScript googleMapsApiKey="AIzaSyDNI_ZWPqvdS6r6gPVO50I4TlYkfkZdXh8">
//                         <GoogleMap
//                             mapContainerClassName="w-full h-full"
//                             center={{ lat, lng }}
//                             zoom={16}
//                             onLoad={() => setMapLoaded(true)}
//                         >
//                             {mapLoaded && lat !== 0 && lng !== 0 && (
//                                 <Marker position={{ lat, lng }} />
//                             )}
//                         </GoogleMap>
//                     </LoadScript>
//                 </div>
//             </div>
//         </div>
//     );
// };

// export default Map;


import React, { useState, useEffect } from 'react';
import { GoogleMap, Marker, LoadScript } from '@react-google-maps/api';
import axios from 'axios';

const Map = () => {
    const [lat, setLat] = useState(0);
    const [lng, setLng] = useState(0);
    const [address, setAddress] = useState('');
    const [mapLoaded, setMapLoaded] = useState(false);

    // Lấy vị trí hiện tại khi tải trang
    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                const { latitude, longitude } = position.coords;
                setLat(latitude);
                setLng(longitude);
            }, (error) => {
                console.error("Error getting location:", error);
            });
        } else {
            console.error("Geolocation is not supported by this browser.");
        }
    }, []);

    // Hàm tìm kiếm địa chỉ và lấy tọa độ từ Google Geocoding API
    const handleSearch = () => {
        console.log(address);
        const API_KEY = 'AIzaSyDNI_ZWPqvdS6r6gPVO50I4TlYkfkZdXh8'; // Thay bằng API key của bạn
        axios
            .get(`https://maps.googleapis.com/maps/api/geocode/json`, {
                params: {
                    address: address,
                    key: API_KEY,
                },
            })
            .then((response) => {
                if (response.data.results.length > 0) { // Kiểm tra nếu có kết quả
                    const location = response.data.results[0].geometry.location;
                
                    console.log("LO1" + location.lat);
                    console.log("LO2" + location.lng);
                } else {
                    console.error("Không tìm thấy địa chỉ.");
                    alert("Không tìm thấy địa chỉ. Vui lòng thử lại.");
                }
            })
            .catch((error) => {
                console.error("Error fetching geolocation:", error);
            });
    };
    

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
            <div className="w-full max-w-4xl bg-white shadow-lg rounded-lg p-6">
                <h2 className="text-2xl font-bold mb-4 text-center">Bản Đồ Hiện Tại</h2>
                {/* Input địa chỉ */}
                <div className="flex items-center mb-4">
                    <input
                        type="text"
                        className="border p-2 rounded w-full"
                        placeholder="Nhập địa chỉ..."
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                    />
                    <button
                        className="bg-blue-500 text-white p-2 rounded ml-2"
                        onClick={handleSearch}
                    >
                        Tìm
                    </button>
                </div>
                {/* Bản đồ Google */}
                <div className="w-full h-96 rounded-lg overflow-hidden">
                    <LoadScript googleMapsApiKey="AIzaSyDNI_ZWPqvdS6r6gPVO50I4TlYkfkZdXh8">
                        <GoogleMap
                            mapContainerClassName="w-full h-full"
                            center={{ lat, lng }}
                            zoom={16}
                            onLoad={() => setMapLoaded(true)}
                        >
                            {mapLoaded && lat !== 0 && lng !== 0 && (
                                <Marker position={{ lat, lng }} />
                            )}
                        </GoogleMap>
                    </LoadScript>
                </div>
            </div>
        </div>
    );
};

export default Map;

