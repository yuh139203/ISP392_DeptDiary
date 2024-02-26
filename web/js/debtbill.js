/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    // Khởi tạo Cleave cho input số tiền
    new Cleave('#amount', {
        numeral: true,
        numeralThousandsGroupStyle: 'thousand'
    });

    // Xác nhận form trước khi submit
    document.getElementById('debtBill').addEventListener('submit', function (event) {
        if (!validateForm()) {
            event.preventDefault();
            console.log('Form không hợp lệ. Vui lòng kiểm tra lại thông tin.');
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const addButton = document.getElementById('addImageButton');
    const container = document.getElementById('imageInputsContainer');

    addButton.addEventListener('click', function () {
        const inputGroup = document.createElement('div');
        inputGroup.classList.add('input-group', 'mb-3');

        const input = document.createElement('input');
        input.type = 'file';
        input.classList.add('form-control');
        input.accept = 'image/png, image/jpeg';
        input.name = 'images[]';

        input.addEventListener('change', function (e) {
            if (e.target.files.length) {
                const file = e.target.files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.style.maxWidth = '400px';
                    img.style.maxHeight = '400px';
                    img.classList.add('img-preview');

                    // Remove any existing previews
                    const existingPreview = inputGroup.querySelector('.img-preview');
                    if (existingPreview) {
                        inputGroup.removeChild(existingPreview);
                    }

                    inputGroup.appendChild(img);
                };

                reader.readAsDataURL(file);
            }
        });

        const removeButton = document.createElement('button');
        removeButton.type = 'button';
        removeButton.classList.add('btn', 'btn-danger');
        removeButton.innerHTML = '<i class="fa fa-minus-circle"></i>';
        removeButton.addEventListener('click', function () {
            inputGroup.remove();
        });

        inputGroup.appendChild(input);
        inputGroup.appendChild(removeButton);

        container.appendChild(inputGroup);
    });
});

//document.addEventListener('DOMContentLoaded', function () {
//    var debtBillForm = document.getElementById('debtBill');
//
//    debtBillForm.addEventListener('submit', function (event) {
//        event.preventDefault(); // Ngăn chặn việc submit form tự động
//
//        if (!validateForm()) {
//            // Nếu validateForm trả về false, hiển thị modal thông báo lỗi
//            $('#errorModal').modal('show');
//        } else {
//            // Nếu không có lỗi, hiển thị modal xác nhận
//            $('#confirmModal').modal('show');
//        }
//    });
//
//    $('#confirmModal').on('shown.bs.modal', function (e) {
//        var confirmButton = document.getElementById('confirmOKButton');
//        confirmButton.onclick = function() {
//            // Gửi form
//            debtBillForm.submit();
//        }
//    });
//});

function validateForm() {
    let isValid = true;
    const debtType = document.querySelector('input[name="debtType"]:checked');
    const amount = document.getElementById('amount').value.trim();
    const date = document.getElementById('date').value.trim();
    const note = document.getElementById('note').value.trim();

    // Reset error messages
    document.getElementById('debtType-error').textContent = '';
    document.getElementById('amount-error').textContent = '';
    document.getElementById('date-error').textContent = '';
    document.getElementById('note-error').textContent = '';

    // Validate debt type
    if (!debtType) {
        document.getElementById('debtType-error').textContent = 'Loại nợ không được để trống.';
        isValid = false;
    }

    // Validate amount
    if (!amount) {
        document.getElementById('amount-error').textContent = 'Số tiền không được để trống.';
        isValid = false;
    }

    // Validate date
    if (!date) {
        document.getElementById('date-error').textContent = 'Ngày lập phiếu không được để trống.';
        isValid = false;
    }

    // Validate note
    if (!note) {
        document.getElementById('note-error').textContent = 'Ghi chú không được để trống.';
        isValid = false;
    }

    return isValid;
}

window.addEventListener('pageshow', function (event) {
    // Kiểm tra nếu trang được tải từ bộ nhớ cache
    if (event.persisted) {
        window.location.reload();
    } else {
        // Reset form ở đây nếu không muốn reload trang
        document.forms[0].reset();
    }
});