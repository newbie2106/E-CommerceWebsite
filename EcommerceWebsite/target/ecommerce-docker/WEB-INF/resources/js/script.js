document.addEventListener("DOMContentLoaded", function () {
    const profileDropdown = document.getElementById('profileDropdown');

    profileDropdown.addEventListener('click', function (event) {
        event.preventDefault(); // Ngăn việc nhảy về đầu trang
        this.classList.toggle('show');
    });

    // Đóng dropdown khi nhấp ra ngoài
    document.addEventListener('click', function (event) {
        if (!profileDropdown.contains(event.target)) {
            profileDropdown.classList.remove('show');
        }
    });
});

function loadDistricts() {
    const provinceId = document.getElementById("provinceId").value;
    const baseUrl = 'http://localhost:8080/EcommerceWebsite';
    if (provinceId) {
        fetch(`${baseUrl}/api/province/${provinceId}/districts/`)
                .then(response => response.json())
                .then(data => {
                    const districtSelect = document.getElementById("districtId");
                    districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>'; // Clear previous options
                    data.forEach(district => {
                        const option = document.createElement("option");
                        option.value = district.code;
                        option.textContent = district.fullName;
                        districtSelect.appendChild(option);
                    });
                    // Clear the ward select when changing province
                    document.getElementById("wardId").innerHTML = '<option value="">Chọn Phường/Xã</option>';
                })
                .catch(error => console.error('Error loading districts:', error));
    }
}

function loadWards() {
    const districtId = document.getElementById("districtId").value;
    const baseUrl = 'http://localhost:8080/EcommerceWebsite';
    if (districtId) {
        fetch(`${baseUrl}/api/district/${districtId}/wards/`)
                .then(response => response.json())
                .then(data => {
                    const wardSelect = document.getElementById("wardId");
                    wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>'; // Clear previous options
                    data.forEach(ward => {
                        const option = document.createElement("option");
                        option.value = ward.code;
                        option.textContent = ward.fullName;
                        wardSelect.appendChild(option);
                    });
                })
                .catch(error => console.error('Error loading wards:', error));
    }
}

function eDelete(url, id) {
    if (confirm("Bạn có chắc chắn muốn xóa?")) {
        fetch(url, {
            method: 'delete'
        })
                .then(res => {
                    if (res.status === 204) {
                        // Xóa thành công
                        alert("Xóa thành công");
                        location.reload(); // Tải lại trang
                    } else {
                        alert("Đã xảy ra lỗi. Vui lòng thử lại sau.");
                    }
                })
                .catch(error => {
                    console.error("Lỗi:", error);
                    alert("Đã xảy ra lỗi. Vui lòng thử lại sau.");
                });
    }
}

function drawChartRevenue(ctx, labels, data, title = "Doanh thu") {
    let colors = [];
    for (let i = 0; i < data.length; i++)
        colors.push(`rgba(${parseInt(Math.random() * 255)}, 
        ${parseInt(Math.random() * 255)}, 
        ${parseInt(Math.random() * 255)}, 0.7)`);

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: title,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: colors
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}