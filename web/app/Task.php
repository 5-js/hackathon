<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Task extends Model
{
    protected $fillable = [
        'title', 'content', 'due_date', 'class_id', 'user_id', 'status'
    ];

    public function classes()
    {
        return $this->belongsTo('App\Classroom', 'class_id', 'id');
    }

    public static function rules()
    {
        return [
            'title'     => 'required',
            'content'   => 'required',
        ];
    }
    public function student()
    {
        return $this->belongsTo('App\User','user_id','id');
    }
}
