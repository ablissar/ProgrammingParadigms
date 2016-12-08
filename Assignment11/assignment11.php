<?php
    function getUserInfo() {
        $file = fopen("assignment11-account-info.txt", "r") or exit("Unable to open user file");
        $index = 0;
        while(!feof($file)) {
            $line = trim(fgets($file));
            if (strlen($line) > 0) {
                $arr = explode(";", $line);
                $users[$index] = $arr[0];
                $passwords[$index] = $arr[1];
                $firstNames[$index] = $arr[2];
                $lastNames[$index] = $arr[3];
                $colors[$index] = $arr[4];
                $titles[$index] = $arr[5];
                $imageLinks[$index] = $arr[6];
                $index = $index + 1;
            }
        }
    }

    getUserInfo();
 ?>

<html>
    <title>Welcome to Adam Bliss's Assignment 11 PHP page!</title>
    <h1>Welcome to Adam Bliss's Assignment 11 PHP page!</h1>
    <body>
        <p> Existing Users:
        <form action="assignment11.php" method="get">
            Username: <input type="text" name="username" /> <br />
            Password: <input type="text" name="password" /> <br />
            <input type="submit" /> <br />
        </form>
        <p> New Users:
        <form action="assignment11.php" method="get">
            Choose your username: <input type="text" name="newUsername" /> <br />
            Choose your password: <input type="text" name="newPassword" /> <br />
            <input type="submit" /> <br />
        </form>
    </body>
</html>
