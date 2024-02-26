<%-- 
    Document   : debtbill
    Created on : 14/02/2024, 9:30:09 PM
    Author     : ussat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
        <meta content="noindex, nofollow" name="robots" />
        <link rel="stylesheet" href="css/debtbill.css">
        <link rel="icon" href="assets/img/logo.png" type="image/png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Debt Detail Modal</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/cleave.js/1.6.0/cleave.min.js"></script>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Bootstrap JS and its dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/cleave.js/1.6.0/cleave.min.js"></script>

    </head>
    <body>
        <section class="container mt-5" id="debtform">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form id="debtBill" method="post" action="bill" enctype="multipart/form-data">
                        <h2>Hóa đơn nợ</h2>
                        <!-- Loại nợ: -->
                        <div class="form-group">
                            <label>Loại nợ:</label>
                            <div class="toggle-buttons">
                                <input type="radio" id="debt" name="debtType" value="debt">
                                <label for="debt" class="btn btn-outline-secondary">Ghi Nợ</label>
                                <input type="radio" id="loan" name="debtType" value="loan">
                                <label for="loan" class="btn btn-outline-secondary">Trả Nợ</label>
                            </div>
                            <div id="debtType-error" class="text-danger"></div>
                        </div>
                        <!-- Số tiền -->
                        <div class="form-group">
                            <label for="amount">Số tiền (*)</label>
                            <input type="text" class="form-control" id="amount" name="amount" placeholder="0" required>
                            <div id="amount-error" class="text-danger"></div>
                        </div>
                        <!-- Ngày lập phiếu -->
                        <div class="form-group">
                            <label for="date">Ngày lập phiếu</label>
                            <input type="datetime-local" id="date" name="date" required>
                            <div id="date-error" class="text-danger"></div>
                        </div>
                        <!-- Ghi chú -->
                        <div class="form-group">
                            <label for="note">Ghi chú</label>
                            <textarea id="note" name="note" rows="4" class="form-control"></textarea>
                            <div id="note-error" class="text-danger"></div>
                        </div>
                        <!-- Hình ảnh -->
                        <div class="form-group">
                            <label>Hình ảnh chứng minh:</label>
                            <button type="button" class="btn btn-primary" id="addImageButton"><i class="fa fa-plus-circle"></i> Thêm Ảnh</button>
                            <div id="imageInputsContainer" style="margin-top: 10px;"></div>
                        </div>

                        <!-- Actions -->
                        <div class="form-actions">
                            <button type="button" class="btn btn-secondary" onclick="document.getElementById('myModal').style.display = 'none'">Close</button>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="js/debtbill.js"></script>      

</body>
</html>