import { useContext, useEffect, useState } from "react";
import { createShippingAddress, loadDistrictsByProvinceCode, loadProvinces, loadWardsByDistrictCode, updateDefaultAddress } from "../configs/APIs";
import { MyUserContext } from "../App";
import { useNavigate } from "react-router-dom";

const AddShippingAddress = () => {
    const [provinces, setProvinces] = useState([]);
    const [districts, setDistricts] = useState([]);
    const [wards, setWards] = useState([]);
    const [selectedProvince, setSelectedProvince] = useState('');
    const [selectedDistrict, setSelectedDistrict] = useState('');
    const [selectedWard, setSelectedWard] = useState('');
    const [fullname, setFullname] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    const [isDefault, setIsDefault] = useState(false); // Thêm state để quản lý isDefault
    const user = useContext(MyUserContext);
    const nav = useNavigate();
    useEffect(() => {
        const fetchProvinces = async () => {
            const result = await loadProvinces();
            setProvinces(result);
        };
        fetchProvinces();
    }, []);

    // Load districts khi chọn province
    useEffect(() => {
        if (selectedProvince) {
            const fetchDistricts = async () => {
                const result = await loadDistrictsByProvinceCode(selectedProvince);
                setDistricts(result);
                setWards([]); // Xóa wards khi province thay đổi
            };
            fetchDistricts();
        }
    }, [selectedProvince]);

    // Load wards khi chọn district
    useEffect(() => {
        if (selectedDistrict) {
            const fetchWards = async () => {
                const result = await loadWardsByDistrictCode(selectedDistrict);
                setWards(result);
            };
            fetchWards();
        }
    }, [selectedDistrict]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const AddShippingAddressRequest = {
            username: user.username,
            fullName: fullname,
            phone,
            address,
            provinceCode: selectedProvince,
            districtCode: selectedDistrict,
            wardCode: selectedWard,
            isDefault
        };

        try {
            const response = await createShippingAddress(AddShippingAddressRequest);
            if (isDefault) {
                const newAddressId = response.id;
                await updateDefaultAddress(newAddressId, user.username);
                
            }
            nav('/address');
            alert("Địa chỉ đã được tạo thành công: " + response);
        } catch (error) {
            alert("Đã có lỗi xảy ra khi tạo địa chỉ: " + error.message);
        }
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-100 flex items-center justify-center p-4">
            <div className="bg-white rounded-lg shadow-xl p-8 w-full max-w-md">
                <h2 className="text-3xl font-bold text-center mb-8 text-gray-800">Thêm địa chỉ mới</h2>
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label className="block mb-1">Họ và Tên:</label>
                        <input
                            type="text"
                            required
                            value={fullname}
                            onChange={(e) => setFullname(e.target.value)}
                            className="w-full p-2 border rounded"
                        />
                    </div>
                    <div>
                        <label className="block mb-1">Số Điện Thoại:</label>
                        <input
                            type="text"
                            required
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                            className="w-full p-2 border rounded"
                        />
                    </div>
                    <div>
                        <label className="block mb-1">Địa Chỉ:</label>
                        <input
                            type="text"
                            required
                            value={address}
                            onChange={(e) => setAddress(e.target.value)}
                            className="w-full p-2 border rounded"
                        />
                    </div>
                    {/* Province Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="province" className="block text-sm font-medium text-gray-700 mb-2">Tỉnh/Thành phố</label>
                        <select
                            id="province"
                            value={selectedProvince}
                            onChange={(e) => setSelectedProvince(e.target.value)}
                            className="block w-full p-2 border rounded"
                            required
                        >
                            <option value="">Chọn Tỉnh/Thành phố</option>
                            {provinces.map(province => (
                                <option key={province.code} value={province.code}>
                                    {province.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* District Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="district" className="block text-sm font-medium text-gray-700 mb-2">Quận/Huyện</label>
                        <select
                            id="district"
                            value={selectedDistrict}
                            onChange={(e) => setSelectedDistrict(e.target.value)}
                            className="block w-full p-2 border rounded"
                            required
                        >
                            <option value="">Chọn Quận/Huyện</option>
                            {districts.map(district => (
                                <option key={district.code} value={district.code}>
                                    {district.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Ward Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="ward" className="block text-sm font-medium text-gray-700 mb-2">Phường/Xã</label>
                        <select
                            id="ward"
                            value={selectedWard}
                            onChange={(e) => setSelectedWard(e.target.value)}
                            className="block w-full p-2 border rounded"
                            required
                        >
                            <option value="">Chọn Phường/Xã</option>
                            {wards.map(ward => (
                                <option key={ward.code} value={ward.code}>
                                    {ward.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Toggle Default Address */}
                    <div className="mb-4">
                        <label className="flex items-center">
                            <input
                                type="checkbox"
                                checked={isDefault}
                                onChange={() => setIsDefault(!isDefault)}
                                className="mr-2"
                            />
                            Đặt làm địa chỉ mặc định
                        </label>
                    </div>

                    <button
                        type="submit"
                        className="mt-4 w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700 transition-colors"
                    >
                        Thêm địa chỉ
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddShippingAddress;
