<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use App\Traits\API;
class UserController extends Controller
{
    use API;
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return $this->respond(['users' => User::all()], 'done');
    }

    public function show($id)
    {
        if (!$this->userExists($id))
            return $this->respond(['message' => 'Whoops! user does not exists'], 'not_found');
        return $this->respond(['user' => User::find($id)], 'done');
    }

    public function update(Request $request, $id)
    {
        
    }

    public function userExists($id)
    {
        return User::find($id)? true : false;
    }

}
