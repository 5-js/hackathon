<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Classroom extends Model
{
    protected $table = 'classes';

    protected $fillable = [
        'id', 'user_id', 'title', 'description'
    ];

    public function teacher()
    {
        return $this->belongsTo('App\User', 'user_id', 'id');
    }

    public function students()
    {
        return $this->hasMany('App\User');
    }

    public static function rules()
    {
        return [
            'title'         => 'required',
            'description'   => 'required'
        ];
    }
}
