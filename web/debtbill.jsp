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
                        <h2>Debt Bill:</h2>
                        <!-- Loại nợ: -->
                        <div class="form-group">
                            <label>Debt Type: ${debtor.fullName}</label>
                            <div class="toggle-buttons">
                                <input type="radio" id="debit" name="debtType" value="debit">
                                <label for="debit" class="btn btn-outline-secondary">Debit</label>
                                <input type="radio" id="credit" name="debtType" value="credit">
                                <label for="credit" class="btn btn-outline-secondary">Credit</label>
                            </div>
                            <div id="debtType-error" class="text-danger"></div> <!-- Added line for displaying error message -->
                        </div>

                        <div id="debitAttributes" style="display: none;">
                            <select class="form-control" id="debitOptions">
                                <option value="">Select an option</option>
                                <option value="1">You lend ${debtor.fullName} money</option> <!-- 2 -->
                                <option value="2">${debtor.fullName} borrows money from you</option><!-- 3 -->
                            </select>
                            <div id="debitOptions-error" class="text-danger"></div>
                        </div>

                        <div id="creditAttributes" style="display: none;">
                            <select class="form-control" id="creditOptions">
                                <option value="">Select an option</option>
                                <option value="1">You borrow money from ${debtor.fullName}</option><!-- 1 -->
                                <option value="2">${debtor.fullName} lends you money</option><!-- 4 -->
                            </select>
                            <div id="creditOptions-error" class="text-danger"></div>
                        </div>

                        <!-- Số tiền -->
                        <div class="form-group">
                            <label for="amount">Amount(*)</label>
                            <input type="text" class="form-control" id="amount" name="amount" placeholder="0" required>
                            <div id="amount-error" class="text-danger"></div>
                        </div>
                        <!-- Ngày lập phiếu -->                   
                        <div class="form-group">
                            <label for="date">Create Date</label>
                            <input type="datetime-local" id="date" name="date" required>
                            <div id="date-error" class="text-danger"></div>
                        </div>                
                        <!-- Hạn nợ -->
                         <div class="form-group">
                             <label for="debtTerm">Debt Term:</label>
                             <input type="date" id="debtTerm" name="debtTerm" class="form-control" required>
                             <div id="debtTerm-error" class="text-danger"></div>
                         </div>
                 
                        <!-- Ghi chú -->
                        <div class="form-group">
                            <label for="note">Description</label>
                            <textarea id="note" name="note" rows="4" class="form-control"></textarea>
                            <div id="note-error" class="text-danger"></div>
                        </div>
                        <!-- Hình ảnh -->
                        <div class="form-group">
                            <label>Evidence Image:</label>
                            <button type="button" class="btn btn-primary" id="addImageButton"><i class="fa fa-plus-circle"></i> Add Photos</button>
                            <div id="imageInputsContainer" style="margin-top: 10px;"></div>
                        </div>

                        <!-- Actions 
                        <div class="form-actions">-->
                        <button type="button" class="btn btn-primary" id="agreeButton">+Add</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- Modal Confirmation -->
<div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel">Confirm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Do you want to create this debt bill ? 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="confirmSubmit">Confirm</button>
            </div>
        </div>
    </div>
</div>
<!-- Success Modal -->
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Success</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Debt Bill add successfully!!!
            </div>
            <div class="modal-footer">
                <!-- Trong modal thành công của bạn -->
                <button type="button" id="closeSuccessModalButton" class="btn btn-default">Close</button>

            </div>
        </div>
    </div>
</div>


<!-- Bootstrap và jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="js/debtbill.js"></script>      

</body>
</html>