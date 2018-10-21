<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Auth;
use App\Traits\API;
use App\Http\Resources\User as UserResource;

class APILoginController extends Controller
{
    use API;
    public function login(Request $request)
    {
       if (!Auth::attempt(['email' => $request->email,'password' => $request->password]))
          return $this->respond(['message' => 'Whoops, invalid credentials!'], 'not_login');

        return $this->respond(['user' => new UserResource(Auth::user())], 'ok');
    }

}
