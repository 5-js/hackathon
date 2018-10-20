<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

// USERS
Route::get('/users', 'UserController@index');
Route::get('/user/{id}', 'UserController@show');
Route::post('/user/{id}', 'UserController@update');

// TASKS
Route::get('/tasks/{id}', 'TaskController@index');
Route::post('/tasks', 'TaskController@store');
Route::get('/task/{id}', 'TaskController@show');
Route::post('/task/{id}', 'TaskController@update');
Route::post('/task/{id}/delete', 'TaskController@destroy');

// CLASSROOM
Route::get('/classes', 'ClassroomController@index');
Route::post('/classes', 'ClassroomController@store');
Route::get('/class/{id}', 'ClassroomController@show');
Route::post('/class/{id}', 'ClassroomController@update');
Route::post('/class/{id}/delete', 'ClassroomController@destroy');
Route::post('/class/{id}/student/add', 'ClassroomController@addStudent');
Route::post('/class/{id}/student/delete', 'ClassroomController@deleteStudent');


