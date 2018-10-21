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
            'due_date'      => date("F d, Y - h:i:s A", strtotime($this->created_at)), 
            'due_human'     => $this->due_date->diffForHumans(), 
            'classroom'     => $this->classes, 
            'student'       => $this->student, 
            'status'        => $this->status,
            'date'          => date("F d, Y", strtotime($this->created_at)), 
            'time'          => date("h:i:s A", strtotime($this->created_at)), 
            'datetime'      => date("F d, Y - h:i:s A", strtotime($this->created_at)), 
            'human_time'    => $this->created_at->diffForHumans(),
        ];
    }
}
