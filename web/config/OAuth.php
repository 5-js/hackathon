<?php

// Configuration Settings for OAUTH

return [
    'clientId'                => env('CLIENT_ID', 'ID'),    // The client ID assigned to you by the provider
    'clientSecret'            => env('CLIENT_SECRET', 'SECRET'),   // The client password assigned to you by the provider
    'redirectUri'             => env('REDIRECT_URI', 'URI'),
    'urlAuthorize'            => env('SERVER_URL_AUTH', ''),
    'urlAccessToken'          => env('SERVER_URL_ACCESS', ''),
    'urlResourceOwnerDetails' => env('SERVER_URL_DEAILS', ''),
    'scopes'                  => ['account_info', 'transfers']
];