<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return ['users' => User::all()];
    }

    public function show($id)
    {
        return ['user'  => User::findOrFail($id)];
    }

    public function update(Request $request, $id)
    {
        //
    }

}
