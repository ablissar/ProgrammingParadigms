<!DOCTYPE HTML>
<script>
function validateForm1() {
    var firstName = document.forms["form1"]["firstName"].value;
    var lastName = document.forms["form1"]["lastName"].value;
    var email = document.forms["form1"]["email"].value;
    if (firstName == "") {
        document.getElementById("validationMessage").innerHTML="First name must be filled out";
        return false;
    }
    else if (lastName == "") {
        document.getElementById("validationMessage").innerHTML="Last name must be filled out";
        return false;
    }
    else if (email == "") {
        document.getElementById("validationMessage").innerHTML="Email must be filled out";
        return false;
    }
    else if (email.indexOf('@') == -1 || email.indexOf('.') == -1) {
        document.getElementById("validationMessage").innerHTML="Email must contain both '@' and '.' characters.";
        return false;
    }
    return true;
}
</script>

<?php
  // Second section
  if( isset($_REQUEST['firstName']) ) { ?>
    <html>
    <body>
      <p> HI <?php echo($_REQUEST['firstName']." ".$_REQUEST['lastName']) ?>! </p>
      <p> Please guess a number between 1 and 5 and submit it below. </p>
      <form name="form2" action="assignment10.php" method="post">
        <input type="text" name="guess" />
        <input type="hidden" name="answer" value=<?php $ans = rand(1,5); echo($ans); ?> />
        <p> <?php echo($ans); ?></p>
        <input type="submit" />
      </form>
    </body>
    </html>
<?php  }
  // If guess is correct
  else if( isset($_POST['guess']) && ($_POST['guess'] == $_POST['answer']) ) { ?>
    <p> Congratulations! Your guess was correct. </p>
<?php }
  // If guess is incorrect
  else if( isset($_POST['guess']) && ($_POST['guess'] != $_POST['answer']) ) { ?>
    <p> Answer was incorrect. Please guess again. </p>
    <form name="form3" action="assignment10.php" method="post">
      <input type="text" name="guess" />
      <input type="hidden" name="answer" value=<?php $_POST['answer'] ?> />
      <?php echo($_POST['answer']); ?>
      <input type="submit" />
    </form>
<?php }
  // First section
  else { ?>
    <html>
    <body>
      <form name="form1" action="assignment10.php" method="get" onsubmit="return validateForm1()">
        First name: <input type="text" name="firstName" value="Adam"/> <br />
        Last name: <input type="text" name="lastName" value="Bliss" /> <br />
        Email address: <input type="text" name="email" value="ab035@uark.edu" /> <br />
        <input type="submit" />
      </form>
      <p id = validationMessage> </p>
    </body>
    </html>
<?php } ?>
