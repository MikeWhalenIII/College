<?php
ini_set('display_startup_errors', 1); // Report compiler (syntax) errors
ini_set('display_errors', 1); // Report run-time errors
error_reporting(E_ALL); // Do not hide any errors

session_start(); // Connect to the existing session
require_once '/home/common/mail.php'; // Add email functionality to program
processPageRequest(); // Call the processPageRequest() function

/**
 *  Adds the specified movie tot he shopping cart.
 *
 * @param $movieID - The movie ID for the movie being added to the shopping cart.
 */
function addMovieToCart($movieID)
{
    $movieIDArray = readMovieData();
    array_push($movieIDArray, $movieID);
    writeMovieData($movieIDArray);
    displayCart();
}

/**
 * Call's the sendMail function to send an email to the user's email address.
 *
 * ================ ERROR CODES ================
 *  0       The email message was sent successfully
 *  1-59    The time (in seconds) that remains before the next email can be sent
 * -1       An error occurred while sending the email message (email not sent)
 * -2       An invalid [mail_id] value was provided (email not sent)
 * -3       An error occurred while accessing the database (email not sent)
 *
 * @param $name - The display name of the user to receive the email.
 * @param $address - The email address of the user.
 */
function checkout($name, $address)
{
    $_SESSION['isEmail'] = true;
    ob_start();
    require_once('./templates/cart_form.html');
    $htmlMessage = ob_get_contents();
    ob_end_clean();
    $_SESSION['isEmail'] = false;

    $result = sendMail(567177288, $address, $name, "Your Receipt from myMovies!", $htmlMessage);
    header('Location: ./logon.php');
}

/**
 * Call's the readMovieData() function and display's the cart.
 */
function displayCart()
{
    $_SESSION['cart_array'] = readMovieData();
    require_once './templates/cart_form.html';
    $_SESSION['isEmail'] = false;
}

/**
 * Test whether the $_GET['action'] value was passed to the page.
 */
function processPageRequest()
{
    if (isset($_GET['action'])) {
        if ($_GET['action'] == 'add') {
            addMovieToCart($_GET['movie_id']);
        } elseif ($_GET['action'] == 'checkout') {
            checkout($_SESSION['display_name'], $_SESSION['email']);
        } elseif ($_GET['action'] == 'remove') {
            removeMovieFromCart($_GET['movie_id']);
        }
    } else {
        displayCart();
    }
}

/**
 * Read's the movieID values fromt he cart.db file into an array.
 *
 * @return array of movies in the cart.
 */
function readMovieData()
{
    // Read the cart.db file and store the values in an array.
    $cart_content = file_get_contents("./data/cart.db");
    return explode(",", $cart_content);
}

/**
 * Remove the specified movie from the shopping cart, then display
 * the updated cart.
 *
 * @param $movieID the movieID to be removed from the shopping cart.
 */
function removeMovieFromCart($movieID)
{
    $movieIDArray = readMovieData();
    $trimmedArray = array_map('trim', $movieIDArray);

    if (($key = array_search($movieID,$trimmedArray)) !== false) {
        unset($trimmedArray[$key]);
    }

    writeMovieData($trimmedArray);
    displayCart();
}

/**
 * Writes the array of movieID's to the cart.db file.
 *
 * @param $array The movieID's to write to the cart.db file.
 */
function writeMovieData($array)
{
    $trimmedArray = array_map('trim', $array);
    $cart_file = fopen("./data/cart.db", "w") or die("Unable to open cart file!");

    // Remove any empty elements
    if (($key = array_search('', $trimmedArray)) !== false) {
        unset($trimmedArray[$key]);
    }
    // If the array is empty, clean contents of file.
    // Else, write the array to the file.
    if (empty($trimmedArray)) {
        fwrite($cart_file,'');
    } else {
        fputcsv($cart_file, $trimmedArray,",");
    }

    // Close the file.
    fclose($cart_file);
}