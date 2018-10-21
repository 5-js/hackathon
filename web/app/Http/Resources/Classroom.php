<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;
use App\Http\Resources\User as UserResource;

class Classroom extends JsonResource
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
            'teacher'       => new UserResource($this->teacher), 
            'title'         => $this->title, 
            'description'   => $this->description,
            'students'      => new UserResource(UserResource::collection($this->students)),
            'date'          => date("F d, Y", strtotime($this->created_at)), 
            'time'          => date("h:i:s A", strtotime($this->created_at)), 
            'datetime'      => date("F d, Y - h:i:s A", strtotime($this->created_at)), 
            'human_time'    => $this->created_at->diffForHumans(),
        ];
    }
}
