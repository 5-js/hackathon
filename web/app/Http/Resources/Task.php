<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class Task extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'title'         => $this->title, 
            'content'       => $this->content, 
            'due_date'      => date("F d, Y - h:i:s A", strtotime($this->due_date)), 
            'classroom'     => $this->classes, 
            'student'       => $this->student, 
            'status'        => $this->status,
            'date'          => $this->created_at->toFormattedDateString("F d, Y"), 
            'time'          => $this->created_at->toFormattedDateString("h:i:s A"), 
            'datetime'      => $this->created_at->toFormattedDateString("F d, Y - h:i:s A"), 
            'human_time'    => $this->created_at->diffForHumans(),
        ];
    }
}
