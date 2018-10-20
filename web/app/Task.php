<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Task extends Model
{
    protected $fillable = [
        'title', 'content', 'due_date', 'class_id'
    ];

    public function classes()
    {
        return $this->belongsTo('App\Class', 'class_id', 'id');
    }

    protected $dates = [
        'due_date'
    ];

    public static function rules()
    {
        return [
            'title'     => 'required',
            'content'   => 'required',
            'dude_date' => 'required'
        ];
    }
}
