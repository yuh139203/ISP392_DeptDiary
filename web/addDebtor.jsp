<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Debtor information</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
              integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="css/debtor.css" rel="stylesheet">

    </head>
    <body>
        <form id="debt-form" action="addDebtor" method="post"enctype="multipart/form-data">
            <div id="debt-info">
                <div>
                    <a href="diary?id=${sessionScope.userLogin.id}"><img class="exit-button" type="button" src="assets/img/reject.png" class="refresh-icon" ></a> 
                </div>
                <div class="form-title">
                    New debtor information
                </div>
                <div>${noti}</div>
                <div class="row mt-2">
                    <div class="col-md-12">
                        <label class="labels">Full Name</label>
                        <input type="text" class="form-control" name="fullName" value="" placeholder="Enter full name" required>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Avatar</label>
                        <input type="file" class="form-control" name="avatar" value="" onchange="previewImage(event)">
                        <img id="avatar-preview" src="#" alt="Preview" style="max-width: 100px; max-height: 150%px; margin-top: 10px; display: none; border-radius: 50%">
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" placeholder="Enter phone number" value="">
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Address</label>
                        <input type="address" class="form-control" name="address" placeholder="Enter address" value="">
                    </div>
                    <div class="col-md-12 total-debt">
                        <label class="labels ">Total Debt</label>
                        <input type="text" class="form-control" value="0" readonly>
                    </div>
                </div>
                <div class="mt-3 text-center">
                    <button class="btn btn-primary profile-button add-button" type="submit" style="width: 200px;"> <i class="fas fa-plus"></i> ADD NEW</button>
                </div>
            </div>
        </form>

    </body>


    <script>
        function previewImage(event) {
            var reader = new FileReader();
reader.onload = function () {
                var output = document.getElementById('avatar-preview');
                output.src = reader.result;
                output.style.display = 'block';
            }
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>

</html>