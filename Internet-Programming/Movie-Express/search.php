<?php
ini_set('display_startup_errors', 1); // Report compiler (syntax) errors
ini_set('display_errors', 1); // Report run-time errors
error_reporting(E_ALL); // Do not hide any errors

session_start(); // Connect to the existing session
processPageRequest(); // Call the processPageRequest() function

/**
 * Displays the search form (search_form.html)
 */
function displaySearchForm()
{
    require_once('./templates/search_form.html');
}

/**
 * Calls the OMDB API using the $searchString to obtain the search result data.
 * @param $searchString
 */
function displaySearchResults($searchString)
{
    $results = file_get_contents('http://www.omdbapi.com/?apikey=af97ecdd&s='.urlencode($searchString).'&type=movie&r=json');
    $_SESSION['SearchArray'] = json_decode($results, true);
    require_once('./templates/results_form.html');
}

/**
 * Test whether any $_POST data was passed to the page.
 */
function processPageRequest()
{
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        if (!empty($_POST["keyword"])) {
            displaySearchResults($_POST["keyword"]);
        }
    } else {
        displaySearchForm();
    }
}