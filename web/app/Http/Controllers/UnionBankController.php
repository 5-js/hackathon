<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use League\OAuth2\Client\Provider\GenericProvider;

class UnionBankController extends Controller
{
    protected $provider;

    public function __construct()
    {
        $this->provider = new GenericProvider([
            'clientId'                =>  config('OAuth.clientId'),
            'clientSecret'            =>  config('OAuth.clientSecret'),
            'redirectUri'             =>  config('OAuth.redirectUri'),
            'urlAuthorize'            =>  config('OAuth.urlAuthorize'),
            'urlAccessToken'          =>  config('OAuth.urlAccessToken'),
            'urlResourceOwnerDetails' =>  config('OAuth.urlResourceOwnerDetails')
        ]);
    }

    public function login(Request $request)
    {
        
    }
}
