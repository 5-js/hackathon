<?php

namespace App\Traits;

trait API 
{
    protected $status_codes = [
        'ok'        => 200,
        'created'   => 201,
        'bad'       => 400,
        'not_login' => 401,
        'forbidden' => 403,
        'not_found' => 404,
        'conflict'  => 409
    ];

    public function respond( $content, $status )
    {
        return response()->json($content, $this->status_codes[$status]);
    }
    
}